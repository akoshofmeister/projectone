package hu.elte.alkfelj.projectone.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "ROOMMEMBERS")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class RoomMember extends BaseEntity {
    @Column(nullable = false)
    private int roomId;
    
    @Column(nullable = false)
    private int userId;
}
