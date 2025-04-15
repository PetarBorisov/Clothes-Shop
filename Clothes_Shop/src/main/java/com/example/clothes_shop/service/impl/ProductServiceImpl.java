package com.example.clothes_shop.service.impl;

import com.example.clothes_shop.dto.ProductAddDTO;
import com.example.clothes_shop.dto.ProductEditDTO;
import com.example.clothes_shop.entity.Product;
import com.example.clothes_shop.repository.ProductsRepository;
import com.example.clothes_shop.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductsRepository productsRepository;
    private final ModelMapper modelMapper;

    public ProductServiceImpl(ProductsRepository productsRepository, ModelMapper modelMapper) {
        this.productsRepository = productsRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public List<Product> getAllProducts() {
        return productsRepository.findAll();
    }

    @Override
    public void addProduct(ProductAddDTO productAddDTO) {

        Optional<Product> product = this.productsRepository.findByName(productAddDTO.getName());

        if (product.isPresent()) {
            Product existingProduct = product.get();

            existingProduct.setName(productAddDTO.getName());
            existingProduct.setDescription(productAddDTO.getDescription());
            existingProduct.setPhoto(productAddDTO.getPhoto());
            existingProduct.setPrice(productAddDTO.getPrice());
            existingProduct.setQuantity(productAddDTO.getQuantity());

            productsRepository.save(existingProduct);

          Product products = this.modelMapper.map(product, Product.class);
            productsRepository.save(products);
        }else {
            Product newProduct = this.modelMapper.map(productAddDTO, Product.class);
            productsRepository.save(newProduct);
        }
    }

    @Override
    public ProductEditDTO getProductById(Long id) {

        Optional<Product> productOptional = productsRepository.findById(id);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            return convertEntityToEditDTO(product);
        }
        
        return null;
    }

    private ProductEditDTO convertEntityToEditDTO(Product product) {

        ProductEditDTO editProduct = new ProductEditDTO();

        editProduct.setId(product.getId());
        editProduct.setName(product.getName());
        editProduct.setDescription(product.getDescription());
        editProduct.setDescription(product.getDescription());
        editProduct.setPhoto(product.getPhoto());
        editProduct.setPrice(product.getPrice());
        editProduct.setQuantity(product.getQuantity());
        return editProduct;
    }

    @Override
    public ProductEditDTO getProductEditDtoById(Long id) {
        Optional<Product> productOptional = productsRepository.findById(id);
        return productOptional.map(product -> modelMapper.map(product, ProductEditDTO.class)).orElse(null);
    }

    @Override
    public void saveProduct(ProductEditDTO productEditDTO) {

        Product product = modelMapper.map(productEditDTO, Product.class);

        productsRepository.save(product);
    }

    @Override
    public void editProduct(Long id, ProductEditDTO productEditDTO) {
        Optional<Product> product = productsRepository.findById(productEditDTO.getId());

        if (product.isPresent()) {
            Product existingProduct = product.get();

            existingProduct.setId(productEditDTO.getId());
            existingProduct.setName(productEditDTO.getName());
            existingProduct.setPhoto(productEditDTO.getPhoto());
            existingProduct.setDescription(productEditDTO.getDescription());
            existingProduct.setQuantity(productEditDTO.getQuantity());
            existingProduct.setPrice(productEditDTO.getPrice());

            productsRepository.save(existingProduct);
        }

    }

    @Override
    public void deleteProduct(Long id) {

        Optional<Product> deleteProduct = this.productsRepository.findById(id);

        deleteProduct.ifPresent(productsRepository::delete);

    }
}
