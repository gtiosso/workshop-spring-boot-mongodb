package com.gtiosso.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.gtiosso.workshopmongo.domain.Post;
import com.gtiosso.workshopmongo.domain.User;
import com.gtiosso.workshopmongo.dto.AuthorDTO;
import com.gtiosso.workshopmongo.dto.CommentDTO;
import com.gtiosso.workshopmongo.repository.PostRepository;
import com.gtiosso.workshopmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Override
	public void run(String... args) throws Exception {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "maria@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		userRepository.saveAll(Arrays.asList(maria, alex, bob));
		
		Post post1 = new Post(null, sdf.parse("21/03/2018"), "Partiu Viagem", "Vou viajar para São Paulo. Abraços!", new AuthorDTO(maria));
		Post post2 = new Post(null, sdf.parse("23/03/2018"), "Bom dia", "Acordei feliz hoje!", new AuthorDTO(maria));
		
		CommentDTO comment1 = new CommentDTO(sdf.parse("22/03/2018"), "Aproveite", new AuthorDTO(bob));
		CommentDTO comment2 = new CommentDTO(sdf.parse("21/03/2018"), "Boa viagem !", new AuthorDTO(alex));
		CommentDTO comment3 = new CommentDTO(sdf.parse("23/03/2018"), "Tenha um otimo dia", new AuthorDTO(bob));
		
		
		post1.setComments(Arrays.asList(comment1, comment2));
		post2.setComments(Arrays.asList(comment3));
		
		postRepository.saveAll(Arrays.asList(post1,post2));
		
		maria.getPosts().addAll(Arrays.asList(post1, post2));
		userRepository.save(maria);
	}

}
