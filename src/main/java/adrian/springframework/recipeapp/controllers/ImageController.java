package adrian.springframework.recipeapp.controllers;

import adrian.springframework.recipeapp.commands.RecipeCommand;
import adrian.springframework.recipeapp.services.ImageService;
import adrian.springframework.recipeapp.services.RecipeService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
public class ImageController {

    private final ImageService imageService;

    private final RecipeService recipeService;

    public ImageController(ImageService imageService, RecipeService recipeService) {
        this.imageService = imageService;
        this.recipeService = recipeService;
    }

    @GetMapping("recipe/{id}/image")
    public String showUploadForm(@PathVariable String id, Model model){

        model.addAttribute("recipe", recipeService.findRecipeCommandById(id).block());
        return "imageUploadForm";
    }

    @PostMapping("recipe/{id}/image")
    public String postImage(@PathVariable String id, @RequestParam("imageFile") MultipartFile file){

        imageService.saveImageFile(id, file).block();
        return "redirect:/recipe/" + id + "/show";
    }

    @GetMapping("recipe/{id}/recipeImage")
    public void renderImageFromDB(@PathVariable String id, HttpServletResponse response) throws IOException {

        RecipeCommand recipeCommand = recipeService.findRecipeCommandById(id).block();
        byte[] byteArray = new byte[recipeCommand.getImage().length];
        int i = 0;
        for (Byte wrappedByte : recipeCommand.getImage()){
            byteArray[i++] = wrappedByte;
        }
        response.setContentType("image/jpeg");
        InputStream inputStream = new ByteArrayInputStream(byteArray);
        IOUtils.copy(inputStream, response.getOutputStream());
    }
}
