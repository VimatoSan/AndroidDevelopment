package com.example.lab1

class ActivityC : BaseActivity() {
    override val layoutId = R.layout.activity_c
    override val buttonId = R.id.btnNextC
    override val nextActivityClass = ActivityA::class.java
}