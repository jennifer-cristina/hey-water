package com.senai.heywater.model

class CalculateDailyIntake {

    private val ml_young = 40.0
    private val ml_adult = 35.0
    private val ml_old = 30.0
    private val ml_more_66_years = 25.0

    private var result_ml = 0.0
    private var result_total_ml = 0.0

    fun CalculateTotalMl(weight: Double, age: Int){

        if(age <= 17){
            result_ml = weight * ml_young
            result_total_ml = result_ml
        }else if(age <= 55){
            result_ml = weight * ml_adult
            result_total_ml = result_ml
        }else if(age <= 65){
            result_ml = weight * ml_old
            result_total_ml = result_ml
        }else{
            result_ml = weight * ml_more_66_years
            result_total_ml = result_ml
        }
    }

    fun ResultMl(): Double{
        return result_total_ml
    }

}