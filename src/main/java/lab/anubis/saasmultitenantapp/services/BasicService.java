package lab.anubis.saasmultitenantapp.services;

import java.util.List;

public interface BasicService<I, O> {

    void create(final I request);
    void update(final  String i, final I request);
    List<O> findAll();
    O findById(final String id);
    void delete(final String id);
}
