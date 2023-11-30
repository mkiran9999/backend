package com.ravikiran.service;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ravikiran.exception.ProductException;
import com.ravikiran.modal.Category;
import com.ravikiran.modal.Deal;
import com.ravikiran.modal.Product;
import com.ravikiran.repository.CategoryRepository;
import com.ravikiran.repository.DealsRepository;
import com.ravikiran.repository.ProductRepository;
import com.ravikiran.request.CreateProductRequest;
//import com.ravikiran.user.domain.ProductSubCategory;

@Service
public class ProductServiceImplementation implements ProductService {
	
	private ProductRepository productRepository;
	private DealsRepository dealsRepository;
	private UserService userService;
	private CategoryRepository categoryRepository;
	
	public ProductServiceImplementation(ProductRepository productRepository,UserService userService,CategoryRepository categoryRepository,DealsRepository dealsRepository) {
		this.productRepository=productRepository;
		this.userService=userService;
		this.categoryRepository=categoryRepository;
		this.dealsRepository = dealsRepository;
	}
	

	@Override
	public Product createProduct(CreateProductRequest req) {
		
		Category topLevel=categoryRepository.findByName(req.getTopLavelCategory());
		
		if(topLevel==null) {
			
			Category topLavelCategory=new Category();
			topLavelCategory.setName(req.getTopLavelCategory());
			topLavelCategory.setLevel(1);
			
			topLevel= categoryRepository.save(topLavelCategory);
		}
		
		Category secondLevel=categoryRepository.
				findByNameAndParant(req.getSecondLavelCategory(),topLevel.getName());
		if(secondLevel==null) {
			
			Category secondLavelCategory=new Category();
			secondLavelCategory.setName(req.getSecondLavelCategory());
			secondLavelCategory.setParentCategory(topLevel);
			secondLavelCategory.setLevel(2);
			
			secondLevel= categoryRepository.save(secondLavelCategory);
		}

		Category thirdLevel=categoryRepository.findByNameAndParant(req.getThirdLavelCategory(),secondLevel.getName());
		if(thirdLevel==null) {
			
			Category thirdLavelCategory=new Category();
			thirdLavelCategory.setName(req.getThirdLavelCategory());
			thirdLavelCategory.setParentCategory(secondLevel);
			thirdLavelCategory.setLevel(3);
			
			thirdLevel=categoryRepository.save(thirdLavelCategory);
		}
		
		
		Product product=new Product();
		product.setTitle(req.getTitle());
		product.setColor(req.getColor());
		product.setDescription(req.getDescription());
		product.setDiscountedPrice(req.getDiscountedPrice());
		product.setDiscountPersent(req.getDiscountPersent());
		product.setImageUrl(req.getImageUrl());
		product.setBrand(req.getBrand());
		product.setPrice(req.getPrice());
		product.setSizes(req.getSize());
		product.setQuantity(req.getQuantity());
		product.setCategory(thirdLevel);
		product.setCreatedAt(LocalDateTime.now());
		
		Product savedProduct= productRepository.save(product);
		
		System.out.println("products - "+product);
		
		return savedProduct;
	}

	@Override
	public String deleteProduct(Long productId) throws ProductException {
		
		Product product=findProductById(productId);
		
		System.out.println("delete product "+product.getId()+" - "+productId);
		product.getSizes().clear();
//		productRepository.save(product);
//		product.getCategory().
		productRepository.delete(product);
		
		return "Product deleted Successfully";
	}

	@Override
	public Product updateProduct(Long productId,Product req) throws ProductException {
		Product product=findProductById(productId);
		
		if(req.getQuantity()!=0) {
			product.setQuantity(req.getQuantity());
		}
		if(req.getDescription()!=null) {
			product.setDescription(req.getDescription());
		}
		
		
			
		
		return productRepository.save(product);
	}

	@Override
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	@Override
	public Product findProductById(Long id) throws ProductException {
		
		Product product = productRepository.findById(id).orElse(null);
		
		if(dealsRepository.findByProductId(id).isPresent()) {
			Deal deal = dealsRepository.findByProductId(id).orElse(null);
			product.setDiscountPersent(deal.getDiscountPersent());
			product.setPrice((int)deal.getPrice());
			product.setDiscountedPrice((int)deal.getDiscountedPrice());
			return product;
		}
			
		
		if (product != null) {
	        return product;
	    }
		throw new ProductException("product not found with id "+id);
	}

	@Override
	public List<Product> findProductByCategory(String category) {
		
		System.out.println("category --- "+category);
		
		List<Product> products = productRepository.findByCategory(category);
		
		List<Product> productsWithoutDeals = products.stream()
		        .filter(p -> !dealsRepository.findByProductId(p.getId()).isPresent())
		        .collect(Collectors.toList());
		
		return productsWithoutDeals;
	}

	@Override
	public List<Product> searchProduct(String query) {
		List<Product> products=productRepository.searchProduct(query);
		return products;
	}



	
	
	@Override
	public Page<Product> getAllProduct(String category, List<String>colors, 
			List<String> sizes, Integer minPrice, Integer maxPrice, 
			Integer minDiscount,String sort, String stock, Integer pageNumber, Integer pageSize ) {

		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		
		List<Product> products = productRepository.filterProducts(category, minPrice, maxPrice, minDiscount, sort);
		
		
		if (!colors.isEmpty()) {
			products = products.stream()
			        .filter(p -> colors.stream().anyMatch(c -> c.equalsIgnoreCase(p.getColor())))
			        .collect(Collectors.toList());
		
		
		} 

		if(stock!=null) {

			if(stock.equals("in_stock")) {
				products=products.stream().filter(p->p.getQuantity()>0).collect(Collectors.toList());
			}
			else if (stock.equals("out_of_stock")) {
				products=products.stream().filter(p->p.getQuantity()<1).collect(Collectors.toList());				
			}
				
					
		}
		
		List<Product> productsWithoutDeals = products.stream()
		        .filter(p -> !dealsRepository.findByProductId(p.getId()).isPresent())
		        .collect(Collectors.toList());
		
		int startIndex = (int) pageable.getOffset();
		int endIndex = Math.min(startIndex + pageable.getPageSize(), productsWithoutDeals.size());

		List<Product> pageContent = productsWithoutDeals.subList(startIndex, endIndex);
		Page<Product> filteredProducts = new PageImpl<>(pageContent, pageable, productsWithoutDeals.size());
	    return filteredProducts; // If color list is empty, do nothing and return all products
		
		
	}

}
