package com.example.springguru.bootstrap;

import com.example.springguru.model.Author;
import com.example.springguru.model.Book;
import com.example.springguru.model.Publisher;
import com.example.springguru.repositories.AuthorRepository;
import com.example.springguru.repositories.BookRepository;
import com.example.springguru.repositories.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
/*Class create objects and insert them to database
 *  */
@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public DevBootstrap(AuthorRepository authorRepository,
                        BookRepository bookRepository,
                        PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    private void initData(){

        Author eric = new Author("Eric", "Evans");
        Publisher er = new Publisher("er");
        publisherRepository.save(er);
        Book ddd = new Book("title", "1234", er);
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);


        authorRepository.save(eric);
        bookRepository.save(ddd);

        Author rod = new Author("Rod", "Hon");
        Publisher hello = new Publisher("Hello");
        publisherRepository.save(hello);
        Book noEjb = new Book("Ejb", "12346", hello);
        rod.getBooks().add(noEjb);
        noEjb.getAuthors().add(rod);

        authorRepository.save(rod);
        bookRepository.save(noEjb);

    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }
}
