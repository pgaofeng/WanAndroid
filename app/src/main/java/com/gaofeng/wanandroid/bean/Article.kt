package com.gaofeng.wanandroid.bean

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey


/**
 * @author 高峰
 * @date 2020/12/3
 *
 * 文章
 */
@Entity
data class Article(
    var apkLink: String = "",
    var audit: Int = 0,
    var author: String = "",
    var canEdit: Boolean = false,
    var chapterId: Int = 0,
    var chapterName: String = "",
    var collect: Boolean = false,
    var courseId: Int = 0,
    var desc: String = "",
    var descMd: String = "",
    var envelopePic: String = "",
    var fresh: Boolean = false,
    @PrimaryKey
    var id: Int = 0,
    var link: String = "",
    var niceDate: String = "",
    var niceShareDate: String = "",
    var origin: String = "",
    var prefix: String = "",
    var projectLink: String = "",
    var publishTime: Long = 0,
    var realSuperChapterId: Int = 0,
    var selfVisible: Int = 0,
    var shareDate: Long? = 0,
    var shareUser: String = "",
    var superChapterId: Int = 0,
    var superChapterName: String = "",
    @Ignore
    var tags: List<Tag> = emptyList(),
    var title: String = "",
    var type: Int = 0,
    var userId: Int = 0,
    var visible: Int = 0,
    var zan: Int = 0
){
    @Ignore
    var isTop = false
}

data class Tag(
    val name: String = "",
    val url: String = ""
)