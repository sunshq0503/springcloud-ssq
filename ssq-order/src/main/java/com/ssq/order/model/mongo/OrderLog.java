package com.ssq.order.model.mongo;

import com.ssq.commons.entity.Product;
import com.ssq.order.entity.Order;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.persistence.Column;
import javax.persistence.Id;
import java.util.List;

@Data
@Document(collection = "OrderLog")
public class OrderLog {
    @Id
    @Column(name = "_id")
    private String id;
    private Order order;
    private List<Product> products;
}
