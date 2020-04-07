package adrian.springframework.recipeapp.services;

import adrian.springframework.recipeapp.repositories.reactive.RecipeReactiveRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import java.io.IOException;

@Service
public class ImageServiceImpl implements ImageService {

    private final RecipeReactiveRepository recipeReactiveRepository;

    public ImageServiceImpl(RecipeReactiveRepository recipeReactiveRepository) {
        this.recipeReactiveRepository = recipeReactiveRepository;
    }

    @Override
    @Transactional
    public Mono<Void> saveImageFile(String recipeId, MultipartFile file) {

        recipeReactiveRepository.findById(recipeId)
                .map(recipe -> {
                    Byte[] byteObj = new Byte[0];
                    try {
                        byteObj = new Byte[file.getBytes().length];

                        int i =0;

                        for(byte b : file.getBytes()){
                            byteObj[i++] = b;
                        }

                        recipe.setImage(byteObj);

                        return recipe;

                    } catch (IOException e) {
                        e.printStackTrace();
                        throw  new RuntimeException(e);
                    }
                }).publish(recipeMono -> recipeReactiveRepository.save(recipeMono.block()));

        return Mono.empty();

       /* try{
            Recipe recipe = recipeReactiveRepository.findById(recipeId).get();
            Byte[] byteObject = new Byte[file.getBytes().length];

            int i = 0;

            for (byte b : file.getBytes()){
                byteObject[i++] = b;
            }

            recipe.setImage(byteObject);
            recipeReactiveRepository.save(recipe);

        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }
}
