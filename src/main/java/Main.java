
import com.amazonaws.regions.Region;
import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.AmazonRekognitionClientBuilder;
import com.amazonaws.services.rekognition.model.Image;
import com.amazonaws.services.rekognition.model.BoundingBox;
import com.amazonaws.services.rekognition.model.CompareFacesMatch;
import com.amazonaws.services.rekognition.model.CompareFacesRequest;
import com.amazonaws.services.rekognition.model.CompareFacesResult;
import com.amazonaws.services.rekognition.model.ComparedFace;

import java.sql.SQLException;
import java.util.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;
import com.amazonaws.util.IOUtils;
import model.Agent;
import model.dal.AgentDAO;
import model.dal.BorrowDAO;
import model.dal.EquipmentDAO;
import org.bytedeco.javacv.CanvasFrame;
import model.dal.PersistenceManager;

public class Main {

    public static void main(String[] args) throws Exception{

        TestDb();

        /*
        Float similarityThreshold = 70F;
        String sourceImage = "C:\\Users\\Remi-\\IdeaProjects\\API\\Images\\test.jpg";
        String targetImage = "C:\\Users\\Remi-\\IdeaProjects\\API\\Images\\RemiCastelPRO.jpg";
        ByteBuffer sourceImageBytes=null;
        ByteBuffer targetImageBytes=null;
        AmazonRekognition rekognitionClient = AmazonRekognitionClientBuilder.defaultClient();
        //Load source and target images and create input parameters
        try (InputStream inputStream = new FileInputStream(new File(sourceImage))) {
            sourceImageBytes = ByteBuffer.wrap(IOUtils.toByteArray(inputStream));
        }
        catch(Exception e)
        {
            System.out.println("Failed to load source image " + sourceImage);
            System.exit(1);
        }
        try (InputStream inputStream = new FileInputStream(new File(targetImage))) {
            targetImageBytes = ByteBuffer.wrap(IOUtils.toByteArray(inputStream));
        }
        catch(Exception e)
        {
            System.out.println("Failed to load target images: " + targetImage);
            System.exit(1);
        }

        Image source=new Image()
                .withBytes(sourceImageBytes);
        Image target=new Image()
                .withBytes(targetImageBytes);

        CompareFacesRequest request = new CompareFacesRequest()
                .withSourceImage(source)
                .withTargetImage(target)
                .withSimilarityThreshold(similarityThreshold);

        // Call operation
        CompareFacesResult compareFacesResult=rekognitionClient.compareFaces(request);


        // Display results
        List <CompareFacesMatch> faceDetails = compareFacesResult.getFaceMatches();
        for (CompareFacesMatch match: faceDetails){
            ComparedFace face= match.getFace();
            BoundingBox position = face.getBoundingBox();
            System.out.println("Face at " + position.getLeft().toString()
                    + " " + position.getTop()
                    + " matches with " + face.getConfidence().toString()
                    + "% confidence.");

        }
        List<ComparedFace> uncompared = compareFacesResult.getUnmatchedFaces();

        System.out.println("There was " + uncompared.size()
                + " face(s) that did not match");
        System.out.println("Source image rotation: " + compareFacesResult.getSourceImageOrientationCorrection());
        System.out.println("target image rotation: " + compareFacesResult.getTargetImageOrientationCorrection());
        */
    }

    private static void TestDb() throws SQLException {
        AgentDAO aDAO = new AgentDAO();

        aDAO.getAgents().forEach(agent -> {
            System.out.println("\nagent id : " + agent.getId()
                    + "\nagent_name : " + agent.getName()
                    + "\nagent_image : " + agent.getImage());
            try {
                System.out.println("\nEQUIPMENTS :");
                aDAO.getEquipmentsFromAgent(agent.getId()).forEach(equipment ->
                        System.out.println("\nequipment_id : " + equipment.getId()
                        + "\nequipment_name : " + equipment.getName()
                        + "\nequipment_quantity : " + equipment.getQuantity()
                        + "\nequipment_is_owned : " + (equipment.isBorrowed() ? "YES" : "NO")));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }
}