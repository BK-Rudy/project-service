package com.infnet.projectservice;

import com.infnet.projectservice.model.Project;
import com.infnet.projectservice.repository.ProjectRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class ProjectRepositoryTest {

    @Autowired
    private ProjectRepository projectRepository;

    @Test
    @DisplayName("Deve salvar um projeto com sucesso")
    void saveTest() {
        Project project = Project.builder()
                .name("Projeto Teste")
                .description("Testando")
                .active(true)
                .progress(50)
                .totalCost(500.0f)
                .estimatedHours(10)
                .budget(1000.0f)
                .address("Rua Faria Lima")
                .build();

        Project savedTask = projectRepository.save(project);

        assertThat(savedTask.getId()).isNotNull();
        assertThat(savedTask.getName()).isEqualTo("Projeto Teste");
        assertThat(savedTask.getDescription()).isEqualTo("Testando");
        assertThat(savedTask.getActive()).isTrue();
        assertThat(savedTask.getProgress()).isEqualTo(50);
        assertThat(savedTask.getTotalCost()).isEqualTo(500.0f);
        assertThat(savedTask.getEstimatedHours()).isEqualTo(10);
        assertThat(savedTask.getBudget()).isEqualTo(1000.0f);
        assertThat(savedTask.getAddress()).isEqualTo("Rua Faria Lima");
    }

    @Test
    @DisplayName("Deve buscar um projeto pelo nome")
    void findByNameTest() {
        Project task = Project.builder()
                .name("Projeto Teste")
                .description("Testando")
                .active(true)
                .progress(50)
                .totalCost(500.0f)
                .estimatedHours(10)
                .budget(1000.0f)
                .address("Rua Faria Lima")
                .build();

        projectRepository.save(task);

        List<Project> foundTasks = projectRepository.findByName("Projeto Teste");

        assertThat(foundTasks).isNotNull();
        assertThat(foundTasks.get(0).getName()).isEqualTo("Projeto Teste");
    }

    @Test
    @DisplayName("Deve atualizar um projeto existente")
    void updateTest() {
        Project task = Project.builder()
                .name("Projeto Teste")
                .description("Testando")
                .active(true)
                .progress(50)
                .totalCost(500.0f)
                .estimatedHours(10)
                .budget(1000.0f)
                .address("Rua Faria Lima")
                .build();

        Project savedTask = projectRepository.save(task);
        savedTask.setName("Projeto Atualizado");
        savedTask.setBudget(2500.0f);

        Project updatedTask = projectRepository.save(savedTask);

        assertThat(updatedTask.getName()).isEqualTo("Projeto Atualizado");
        assertThat(updatedTask.getBudget()).isEqualTo(2500.0f);
    }

    @Test
    @DisplayName("Deve deletar um projeto com sucesso")
    void deleteTest() {
        Project task = Project.builder()
                .name("Projeto Teste")
                .description("Testando")
                .active(true)
                .progress(50)
                .totalCost(500.0f)
                .estimatedHours(10)
                .budget(1000.0f)
                .address("Rua Faria Lima")
                .build();

        Project savedTask = projectRepository.save(task);

        projectRepository.deleteById(savedTask.getId());

        Optional<Project> deletedTask = projectRepository.findById(savedTask.getId());

        assertThat(deletedTask).isEmpty();
    }
}
