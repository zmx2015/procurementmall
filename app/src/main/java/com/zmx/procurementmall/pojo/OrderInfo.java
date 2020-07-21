package com.zmx.procurementmall.pojo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

    public class OrderInfo {

        private String orderNumber;

        private Integer oUid;

        private Long orderTime;

        private Integer oPayment;

        private Long paymentTime;

        private String outTradeNo;

        private Integer shipmentStatus;

        private String shippingAddress;

        private BigDecimal goodsRental;

        private BigDecimal actualPayment;
        private Integer orderState;

        private List<OrderProduct> lists;

        public List<OrderProduct> getLists() {
            return lists;
        }

        public void setLists(List<OrderProduct> lists) {
            this.lists = lists;
        }

        public String getOrderNumber() {
            return orderNumber;
        }

        public void setOrderNumber(String orderNumber) {
            this.orderNumber = orderNumber;
        }

        public Integer getoUid() {
            return oUid;
        }

        public void setoUid(Integer oUid) {
            this.oUid = oUid;
        }

        public Long getOrderTime() {
            return orderTime;
        }

        public void setOrderTime(Long orderTime) {
            this.orderTime = orderTime;
        }

        public Integer getoPayment() {
            return oPayment;
        }

        public void setoPayment(Integer oPayment) {
            this.oPayment = oPayment;
        }

        public Long getPaymentTime() {
            return paymentTime;
        }

        public void setPaymentTime(Long paymentTime) {
            this.paymentTime = paymentTime;
        }

        public String getOutTradeNo() {
            return outTradeNo;
        }

        public void setOutTradeNo(String outTradeNo) {
            this.outTradeNo = outTradeNo;
        }

        public Integer getShipmentStatus() {
            return shipmentStatus;
        }

        public void setShipmentStatus(Integer shipmentStatus) {
            this.shipmentStatus = shipmentStatus;
        }

        public String getShippingAddress() {
            return shippingAddress;
        }

        public void setShippingAddress(String shippingAddress) {
            this.shippingAddress = shippingAddress;
        }

        public BigDecimal getGoodsRental() {
            return goodsRental;
        }

        public void setGoodsRental(BigDecimal goodsRental) {
            this.goodsRental = goodsRental;
        }

        public BigDecimal getActualPayment() {
            return actualPayment;
        }

        public void setActualPayment(BigDecimal actualPayment) {
            this.actualPayment = actualPayment;
        }

        public Integer getOrderState() {
            return orderState;
        }

        public void setOrderState(Integer orderState) {
            this.orderState = orderState;
        }
    }