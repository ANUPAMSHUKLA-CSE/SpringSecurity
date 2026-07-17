package com.AnupamSecurity.demo.DO.Request;



public record CreateRequest(
        String vehicleName,
        Integer vehicleNumber,
        Integer capacity,
        Integer amountCharged
) {
}
