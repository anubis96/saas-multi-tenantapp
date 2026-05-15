package lab.anubis.saasmultitenantapp.services;

import lab.anubis.saasmultitenantapp.common.PageResponse;
import lab.anubis.saasmultitenantapp.requests.StockMvtRequest;
import lab.anubis.saasmultitenantapp.responses.StockMvtResponse;

public interface StockMvtService extends BasicService<StockMvtRequest, StockMvtResponse>{
    PageResponse<StockMvtResponse> findAllByProductId(final String productId, final int page, final int size);
}
