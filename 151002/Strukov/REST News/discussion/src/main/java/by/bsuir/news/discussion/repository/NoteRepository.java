package by.bsuir.news.discussion.repository;

import by.bsuir.news.discussion.entity.Note;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NoteRepository extends CassandraRepository<Note, Long> {
    @Query(allowFiltering = true)
    Optional<Note> findByKeyId(Long id);
}
