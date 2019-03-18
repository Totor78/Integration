package controller;

import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.AmazonRekognitionClientBuilder;
import com.amazonaws.services.rekognition.model.*;
import com.amazonaws.util.IOUtils;
import model.dal.AgentDAO;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class FacesComparisonController {

    public FacesComparisonController() {
    }

    public int run() throws SQLException {
        Float similarityThreshold = 60F;
        AtomicReference<ByteBuffer> sourceImageBytes = new AtomicReference<>();
        String finalSourceImage = "./Images/capture.jpg";
        try (InputStream inputStream = new FileInputStream(new File(finalSourceImage))) {
            sourceImageBytes.set(ByteBuffer.wrap(IOUtils.toByteArray(inputStream)));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to load source image " + finalSourceImage);
            System.exit(1);
        }
        AtomicInteger agentId = new AtomicInteger();
        new AgentDAO().getAgents().forEach(agent -> {
            String targetImage = "./Images/" + agent.getImage() + ".jpg";
            ByteBuffer targetImageBytes = null;
            AmazonRekognition rekognitionClient = AmazonRekognitionClientBuilder.defaultClient();
            //Load source and target images and create input parameters
            try (InputStream inputStream = new FileInputStream(new File(targetImage))) {
                targetImageBytes = ByteBuffer.wrap(IOUtils.toByteArray(inputStream));
            } catch (Exception e) {
                System.out.println("Failed to load target images: " + targetImage);
                System.exit(1);
            }

            Image source = new Image()
                    .withBytes(sourceImageBytes.get());
            Image target = new Image()
                    .withBytes(targetImageBytes);

            CompareFacesRequest request = new CompareFacesRequest()
                    .withSourceImage(source)
                    .withTargetImage(target)
                    .withSimilarityThreshold(similarityThreshold);

            // Call operation
            System.out.println(request);
            try {
                CompareFacesResult compareFacesResult = rekognitionClient.compareFaces(request);
                // Display results
                List<CompareFacesMatch> faceDetails = compareFacesResult.getFaceMatches();
                for (CompareFacesMatch match : faceDetails) {
                    ComparedFace face = match.getFace();
                    agentId.set(agent.getId());
                    break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        System.out.println(agentId.intValue());
        return agentId.intValue();
    }
}
