package vn.edu.iuh.fit.backend.services;

import java.util.List;

public interface IService<S,P> {
    S add(S s);
    S update (S s);
    boolean deleteById(P p);
    S findById(P p);
    List<S> findAll();
}
