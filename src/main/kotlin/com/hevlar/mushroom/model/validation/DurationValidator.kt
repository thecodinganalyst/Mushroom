package com.hevlar.mushroom.model.validation

import com.hevlar.mushroom.model.DurationRecording
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext

class DurationValidator : ConstraintValidator<ValidDuration, DurationRecording> {

    override fun isValid(durationRecording: DurationRecording, context: ConstraintValidatorContext): Boolean {
        if (durationRecording.until == null) return true
        return durationRecording.until!!.isAfter(durationRecording.dateTime)
    }
}
