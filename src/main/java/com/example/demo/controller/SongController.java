package com.example.demo.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.Song;
import com.example.demo.services.SongService;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class SongController {
	@Autowired
	SongService service;
	@PostMapping("/addSong")
	public String addSong(@ModelAttribute Song song) {

		boolean songStatus = service.songExists(song.getName());
		if(songStatus == false) {
			service.addSong(song);
			System.out.println("Song added Successfully" );

		}
		else {
			System.out.println("Song already exists");
		}
		return "adminHome";
	}
	@GetMapping("/viewSongs")
	public String getMethodName(Model model) {
		model.addAttribute("Songs", service.fetchAllSongs());
		return "displaySongs";
	}


	@GetMapping("/playSongs")
	public String playSongs( Model model) {
		boolean PremiumUser= false;
		if(PremiumUser==true)
		{
			List<Song> songsList =  service.fetchAllSongs();
			model.addAttribute("Songs",songsList);
			return "displaySongs";
		}
		else {

			return "makePayment";
		}


	}
}


