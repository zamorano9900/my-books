package com.zamorano.mybooks.model.db

import com.zamorano.mybooks.model.api.Book
import com.zamorano.mybooks.model.db.dbEntities.BookEntity
import com.zamorano.mybooks.model.db.schema.AppDatabase

class BookDbRepository : BaseDbRepository() {
    companion object {
        fun saveAll(lstCategories: List<Book>, database: AppDatabase) {
            lstCategories.forEach { p ->
                executeQuery {
                    val exist = database.booksDao().getById(p.isbn)
                    if (exist.count() == 0) {
                        database.booksDao().insert(
                            bookToBookEntity(p)
                        )
                    }
                }
            }
        }

        fun getAll(database: AppDatabase): List<Book> {
            var lstCategories: List<BookEntity>? = null
            executeQuery {
                lstCategories = database.booksDao().getAll()
            }
            return lstCategories!!.map { bookEntityToBook(it) }
        }

        private fun bookToBookEntity(book: Book): BookEntity {
            return BookEntity(
                book.isbn,
                book.name,
                book.authors,
                book.numberOfPages,
                book.publisher,
                book.country,
                book.mediaType,
                book.released
            )
        }

        private fun bookEntityToBook(bookEntity: BookEntity): Book {
            return Book(
                bookEntity.isbn,
                bookEntity.name,
                bookEntity.authors,
                bookEntity.numberOfPages,
                bookEntity.publisher,
                bookEntity.country,
                bookEntity.mediaType,
                bookEntity.released
            )
        }
    }
}
