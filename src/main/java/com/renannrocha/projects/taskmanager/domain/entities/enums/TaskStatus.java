package com.renannrocha.projects.taskmanager.domain.entities.enums;

public enum TaskStatus {
    PENDENTE(1),
    EM_ANDAMENTO(2),
    CONCLUIDO(3);

    private Integer codigo;

    TaskStatus(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public static TaskStatus valueOf(int code) {
        for(TaskStatus value : TaskStatus.values()) {
            if(value.getCodigo() == code) {
                return value;
            }
        }
        throw new IllegalArgumentException("Código de status de tarefa invalido!");
    }
}
