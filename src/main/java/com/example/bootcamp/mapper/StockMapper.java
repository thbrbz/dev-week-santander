package com.example.bootcamp.mapper;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.stereotype.Component;

import com.example.bootcamp.model.Stock;
import com.example.bootcamp.model.dto.StockDTO;

@Component
public class StockMapper {

	public Stock toEntity(@Valid StockDTO dto) {
		
		Stock stock = new Stock();
		stock.setId(dto.getId());
		stock.setName(dto.getName());
		stock.setPrice(dto.getPrice());
		stock.setVariation(dto.getVariation());
		stock.setDate(dto.getDate());
		
		return stock;
	}

	public StockDTO toDto(Stock stock) {
		
		StockDTO dto = new StockDTO();
		dto.setId(dto.getId());
		dto.setName(dto.getName());
		dto.setPrice(dto.getPrice());
		dto.setVariation(dto.getVariation());
		dto.setDate(dto.getDate());
		
		return dto;
	}

	public List<StockDTO> toDto(List<Stock> ListStock) {
		return ListStock.stream().map(this::toDto).collect(Collectors.toList());
	}
}
