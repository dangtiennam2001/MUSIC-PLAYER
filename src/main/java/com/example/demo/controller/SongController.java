package com.example.demo.controller;

import com.example.demo.entity.Song;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class SongController {
    @Value("${upload.directory}")
    private String uploadDirectory;

    @GetMapping("/")
    public String index(Model model) {
        // Lấy danh sách các tệp nhạc đã upload từ thư mục
        File[] files = new File(uploadDirectory).listFiles();
        List<Song> songs = Arrays.stream(files)
                .map(file -> new Song(file.getName(), file.getName()))
                .collect(Collectors.toList());
        model.addAttribute("songs", songs);
        return "index";
    }

    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file) {
        // Lưu tệp nhạc vào thư mục upload
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                Path path = Paths.get(uploadDirectory + "/" + file.getOriginalFilename());
                Files.write(path, bytes);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "redirect:/";
    }

    @GetMapping("/delete/{filename}")
    public String delete(@PathVariable("filename") String filename) {
        Path filePath = Paths.get(uploadDirectory, filename);
        try {
            Files.deleteIfExists(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/";
    }
}
