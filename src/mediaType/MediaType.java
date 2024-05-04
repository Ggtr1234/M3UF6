package mediaType;

public class MediaType {
    private int mediaTypeId;
    private String name;

    public MediaType(int mediaTypeId, String name) {
        this.mediaTypeId = mediaTypeId;
        this.name = name;
    }

    public MediaType() {
    }

    public int getMediaTypeId() {
        return mediaTypeId;
    }

    public void setMediaTypeId(int mediaTypeId) {
        this.mediaTypeId = mediaTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "MediaType{" +
                "mediaTypeId=" + mediaTypeId +
                ", name='" + name + '\'' +
                '}';
    }
}
