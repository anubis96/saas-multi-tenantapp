package lab.anubis.saasmultitenantapp.services.impl;

import jakarta.persistence.EntityNotFoundException;
import lab.anubis.saasmultitenantapp.common.PageResponse;
import lab.anubis.saasmultitenantapp.entities.Product;
import lab.anubis.saasmultitenantapp.entities.StockMvt;
import lab.anubis.saasmultitenantapp.mapper.StockMvtMapper;
import lab.anubis.saasmultitenantapp.repositories.ProductRepository;
import lab.anubis.saasmultitenantapp.repositories.StockMvtRepository;
import lab.anubis.saasmultitenantapp.requests.StockMvtRequest;
import lab.anubis.saasmultitenantapp.responses.StockMvtResponse;
import lab.anubis.saasmultitenantapp.services.StockMvtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class StockMvtServiceImpl implements StockMvtService {

    private final StockMvtRepository stockMvtRepository;
    private final ProductRepository productRepository;
    private final StockMvtMapper stockMvtMapper;

    @Override
    public void create(StockMvtRequest request) {
        // check if product exists
        checkIfProductExistsById(request.getProductId());

        final StockMvt entity = this.stockMvtMapper.toEntity(request);
        entity.setDateMvt(LocalDate.now());
        this.stockMvtRepository.save(entity);
    }

    @Override
    public void update(String id, StockMvtRequest request) {
        final Optional<StockMvt> stockMvt = this.stockMvtRepository.findById(id);
        if (stockMvt.isEmpty()) {
            log.debug("StockMvt does not exist");
            throw new EntityNotFoundException("StockMvt does not exist");
        }

        // check if product exists
        checkIfProductExistsById(request.getProductId());

        final StockMvt stockMvtToUpdate = this.stockMvtMapper.toEntity(request);
        stockMvtToUpdate.setDateMvt(LocalDate.now());
        stockMvtToUpdate.setId(id);
        this.stockMvtRepository.save(stockMvtToUpdate);
    }

    @Override
    public List<StockMvtResponse> findAllList() {
        return List.of();
    }

    @Override
    public PageResponse<StockMvtResponse> findAll(int page, int size) {
        final PageRequest pageRequest = PageRequest.of(page, size);
        final Page<StockMvt> stockMvts = this.stockMvtRepository.findAll(pageRequest);
        final Page<StockMvtResponse> stockMvtResponses = stockMvts.map(this.stockMvtMapper::toResponse);
        return PageResponse.of(stockMvtResponses);
    }

    @Override
    public StockMvtResponse findById(String id) {
        return this.stockMvtRepository.findById(id)
                .map(this.stockMvtMapper::toResponse)
                .orElseThrow(() -> new EntityNotFoundException("StockMvt does not exist"));
    }

    @Override
    public void delete(String id) {
        final StockMvt stockMvt = this.stockMvtRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("StockMvt does not exist"));
        this.stockMvtRepository.delete(stockMvt);
    }

    @Override
    public PageResponse<StockMvtResponse> findAllByProductId(String productId, int page, int size) {
        final PageRequest pageRequest = PageRequest.of(page, size);
        final Page<StockMvt> stockMvts = this.stockMvtRepository.findAllByProductId(productId, pageRequest);
        final Page<StockMvtResponse> stockMvtResponses = stockMvts.map(this.stockMvtMapper::toResponse);
        return PageResponse.of(stockMvtResponses);
    }

    private void checkIfProductExistsById(final String productId) {
        final Optional<Product> product = this.productRepository.findById(productId);
        if (product.isEmpty()) {
            log.debug("Product does not exist");
            throw new EntityNotFoundException("Product does not exist");
        }
    }
}
