package ua.dark.catparser.repo;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;
import ua.dark.catparser.entity.Cat;

@Repository
public interface CatRepository extends CassandraRepository<Cat, String> {
}
