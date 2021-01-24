package com.andresimiquelli.workshopmongo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andresimiquelli.workshopmongo.domain.Post;
import com.andresimiquelli.workshopmongo.repository.PostRepository;
import com.andresimiquelli.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class PostService {

	@Autowired
	private PostRepository repository;


	public Post findById(String id) {
		Optional<Post> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Post not found"));
	}
	
}
