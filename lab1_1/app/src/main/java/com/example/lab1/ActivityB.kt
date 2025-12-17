package com.example.lab1

class ActivityB : BaseActivity() {
    override val layoutId = R.layout.activity_b
    override val buttonId = R.id.btnNextB
    override val nextActivityClass = ActivityC::class.java
}