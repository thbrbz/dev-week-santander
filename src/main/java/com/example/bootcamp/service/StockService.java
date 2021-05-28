package com.example.bootcamp.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.example.bootcamp.exception.BusinessException;
import com.example.bootcamp.exception.NotFoundException;
import com.example.bootcamp.mapper.StockMapper;
import com.example.bootcamp.model.Stock;
import com.example.bootcamp.model.dto.StockDTO;
import com.example.bootcamp.repository.StockRepository;
import com.example.bootcamp.util.MessageUtils;

public class StockService {

	@Autowired
	private StockRepository repository;
	
	@Autowired
	private StockMapper mapper;

	@Transactional
	public StockDTO save(@Valid StockDTO dto) {

		Optional<Stock> optionalStock = repository.findByNameAndDate(dto.getName(),dto.getDate());
		
		if (optionalStock.isPresent()) {
			throw new BusinessException(MessageUtils.STOCK_ALREADY_EXISTS);
		}

		Stock stock = mapper.toEntity(dto);
		repository.save(stock);
		
		return mapper.toDto(stock);
	}

	@Transactional
	public StockDTO update(@Valid StockDTO dto) {
		Optional<Stock> optionalStock = repository.findByStockUpdate(dto.getName(), dto.getDate(), dto.getId());
		
		if (optionalStock.isPresent()) {
			throw new BusinessException(MessageUtils.STOCK_ALREADY_EXISTS);
		}
		
		Stock stock = mapper.toEntity(dto);
		repository.save(stock);
		
		return mapper.toDto(stock);
	}

	@Transactional(readOnly = true)
	public List<StockDTO> findAll() {
		return mapper.toDto(repository.findAll());
	}

	@Transactional(readOnly = true)
	public StockDTO findById(Long id) {
		return repository.findById(id).map(mapper::toDto).orElseThrow(NotFoundException::new);
	}

	public StockDTO delete(Long id) {

		StockDTO dto = this.findById(id);
		repository.deleteById(id);

		return dto;
	}

	public List<StockDTO> findByToday() {
		return repository.findByToday(LocalDate.now()).map(mapper::toDto).orElseThrow(NotFoundException::new);
	}

}
