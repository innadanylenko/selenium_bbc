package base;

import static java.util.Objects.isNull;

public class News {
    private String link;
    private String topic;
    private String description;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        return link.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (isNull(obj)) {
            return false;
        }
        return link.equals(((News) obj).link);
    }
}
