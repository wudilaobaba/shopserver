/**
 * @作者 7七月
 * @微信公号 林间有风
 * @开源项目 $ http://7yue.pro
 * @免费专栏 $ http://course.7yue.pro
 * @我的课程 $ http://imooc.com/t/4294850
 * @创建时间 2019-08-28 14:58
 */
package com.whj.shop.shopserver.modelReal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.type.TypeReference;
import com.whj.shop.shopserver.core.enumeration.OrderStatus;
import com.whj.shop.shopserver.dto.OrderAddressDTO;
import com.whj.shop.shopserver.util.CommonUtil;
import com.whj.shop.shopserver.util.GenericAndJson;
import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

//import com.lin.missyou.core.enumeration.OrderStatus;

//import com.lin.missyou.util.ListAndJson;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Where(clause = "delete_time is null")
@Table(name = "`Order`")
public class OrderEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String orderNo;
    private Long userId;
    private BigDecimal totalPrice;
    private Long totalCount;
    private String snapImg;
    private String snapTitle;

    private String snapItems;

    private String snapAddress;

    private String prepayId;
    private BigDecimal finalTotalPrice;
    private Integer status;
    private Date expiredTime;

    @JsonIgnore
    public OrderStatus getStatusEnum() {
        return OrderStatus.toType(this.status);
    }

    public Boolean needCancel(Long period) {
        if(!this.getStatusEnum().equals(OrderStatus.UNPAID)){
            return false;
        }
        boolean isOutOfDate = CommonUtil.isOutOfDate(this.getCreateTime(), period);
        if(isOutOfDate){
            return true;
        }
        return false;
    }

//    public void setSnapItems(List<OrderSku> orderSkuList) {
//        if (orderSkuList.isEmpty()) {
//            return;
//        }
//        this.snapItems = GenericAndJson.objectToJson(orderSkuList);
//    }
//
//    public List<OrderSku> getSnapItems() {
//        List<OrderSku> list = GenericAndJson.jsonToList(this.snapItems,
//                new TypeReference<>() {
//                });
//        return list;
//    }
//
//    public OrderAddressDTO getSnapAddress() {
//        if (this.snapAddress == null) {
//            return null;
//        }
//        OrderAddressDTO o = GenericAndJson.jsonToObject(this.snapAddress,
//                OrderAddressDTO.class);
//        return o;
//    }
//
//    public void setSnapAddress(OrderAddressDTO address) {
//        this.snapAddress = GenericAndJson.objectToJson(address);
//    }
}
