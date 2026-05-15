package lab.anubis.saasmultitenantapp.services;

import lab.anubis.saasmultitenantapp.common.PageResponse;

import java.util.List;

public interface BasicService<I, O> {

    void create(final I request);
    void update(final  String id, final I request);
    List<O> findAllList();
    PageResponse<O> findAll(final int page, final int size);
    O findById(final String id);
    void delete(final String id);
}
