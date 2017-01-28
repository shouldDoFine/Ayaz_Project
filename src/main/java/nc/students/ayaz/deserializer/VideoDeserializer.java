package nc.students.ayaz.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import nc.students.ayaz.model.Comment;
import nc.students.ayaz.model.Video;

import java.io.IOException;

public class VideoDeserializer extends JsonDeserializer<Video> {

    @Override
    public Video deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        ObjectCodec codec = jsonParser.getCodec();
        JsonNode node = codec.readTree(jsonParser);
        Video video = new Video(node.get("name").asText());

        JsonNode arrayNode = node.get("comments");
        for (JsonNode arrayNodeObject : arrayNode) {
            String author = arrayNodeObject.get("author").asText();
            String text = arrayNodeObject.get("text").asText();
            video.addComment(new Comment(author, text));
        }

        return video;
    }
}
