package pl.sda.library.infrastructure.jpa;

import lombok.*;
import pl.sda.library.domain.model.Category;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "categories")
class CategoryDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_category")
    private Long idCategory;
    private String name;

    public Category toModel() {
        Category category=new Category();
        category.setIdCategory(getIdCategory());
        category.setName(getName());
        return category;
    }
}