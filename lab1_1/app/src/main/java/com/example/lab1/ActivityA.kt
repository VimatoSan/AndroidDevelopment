package com.example.lab1

class ActivityA : BaseActivity() {
    override val layoutId = R.layout.activity_a
    override val buttonId = R.id.btnNextA
    override val nextActivityClass = ActivityB::class.java
}

