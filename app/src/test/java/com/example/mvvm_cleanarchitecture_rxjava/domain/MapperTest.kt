package com.example.mvvm_cleanarchitecture_rxjava.domain

import com.example.mvvm_cleanarchitecture_rxjava.data.models.Album
import com.example.mvvm_cleanarchitecture_rxjava.data.models.Photo
import com.example.mvvm_cleanarchitecture_rxjava.data.models.User
import org.junit.Assert
import org.junit.Test


class MapperTest {

    @Test
    fun test_Album_ToDomain() {
        val album = Album(
            id = 123,
            userId = 989,
            title = "Title"
        )

        val model = album.toDomainModel()

        Assert.assertEquals(123, model.id)
        Assert.assertEquals(989, model.userId)
        Assert.assertEquals("Title", model.title)
    }

    @Test
    fun test_Photo_toDomain() {
        val photo = Photo(
            id = 123,
            albumId = 767,
            url = "testimage.com",
            title = "Title",
            thumbnailUrl = "testimage_thumbnail.com"
        )

        val model = photo.toDomainModel()

        Assert.assertEquals(123, model.id)
        Assert.assertEquals(767, model.albumId)
        Assert.assertEquals("Title", model.title)
        Assert.assertEquals("testimage_thumbnail.com", model.thumbnailUrl)

    }

    @Test
    fun test_User_toDomain() {
        val user = User(
            id = 123,
            name = "Irshad Kumail",
            username = "irshadkumail",
            email = "irshad@gmail.com",
            phone = "565123",
            website = "test.com"
        )

        val model = user.toDomainModel()

        Assert.assertEquals(123, model.id)
        Assert.assertEquals("irshad@gmail.com", model.email)
        Assert.assertEquals("Irshad Kumail", model.name)
        Assert.assertEquals("565123", model.phone)
        Assert.assertEquals("test.com", model.website)
    }

}