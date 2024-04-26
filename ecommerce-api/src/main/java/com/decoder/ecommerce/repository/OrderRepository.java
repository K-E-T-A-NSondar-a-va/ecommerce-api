package com.decoder.ecommerce.repository;

import com.decoder.ecommerce.model.PurchaseOrder;
import com.decoder.ecommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<PurchaseOrder, Long> {

    @Query("SELECT po FROM PurchaseOrder po WHERE po.user.id = :userId AND (po.orderStatus = 'PLACED' OR po.orderStatus = 'CONFIRMED' OR po.orderStatus = 'SHIPPED' OR po.orderStatus = 'DELIVERED')")
    public List<PurchaseOrder> getUserOrders(@Param("userId") Long userId);

    List<PurchaseOrder> findByUser(User user);
}
