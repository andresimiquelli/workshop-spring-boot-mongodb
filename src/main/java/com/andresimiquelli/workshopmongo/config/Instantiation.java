package com.andresimiquelli.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.andresimiquelli.workshopmongo.domain.Post;
import com.andresimiquelli.workshopmongo.domain.User;
import com.andresimiquelli.workshopmongo.dto.AuthorDTO;
import com.andresimiquelli.workshopmongo.repository.PostRepository;
import com.andresimiquelli.workshopmongo.repository.UserRepository;

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
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		userRepository.saveAll(Arrays.asList(maria,alex,bob));
		
		Post p1 = new Post(null, sdf.parse("21/08/2018"), "Partiu viagem", "Pessoal, estou indo pra Europa amanhã.", new AuthorDTO(maria));
		Post p2 = new Post(null, sdf.parse("20/01/2019"), "Dicas de vigem no Brasil", "Gente, quero dicas de destinos no Brasil.", new AuthorDTO(maria));
		
		postRepository.saveAll(Arrays.asList(p1,p2));
		
		maria.getPosts().addAll(Arrays.asList(p1,p2));
		userRepository.save(maria);
	}

}
