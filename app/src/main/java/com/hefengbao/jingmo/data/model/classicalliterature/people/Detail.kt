/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.data.model.classicalliterature.people

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * 人物资料
 */
@Serializable
data class Detail(
    @SerialName("Book")
    val book: String,
    @SerialName("Section")
    val section: String?,
    @SerialName("Content")
    val content: String?,
    @SerialName("IsReview")
    val isReview: Boolean,
    @SerialName("ReferrenceUrls")
    val referenceUrls: List<String>?
)
