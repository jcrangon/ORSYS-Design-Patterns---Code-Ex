import java.time.Instant; import java.util.UUID;
public record Order(UUID id, int total, Instant createdAt) {}
