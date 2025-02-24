package ec.edu.uees.modelo;

public class VideoNode {
    public String videoName;
    public VideoNode next;
    public VideoNode prev;

    public VideoNode(String videoName) {
        this.videoName = videoName;
    }
}
