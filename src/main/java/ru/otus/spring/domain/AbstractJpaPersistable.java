package ru.otus.spring.domain;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.util.ProxyUtils;

import java.io.Serializable;

@MappedSuperclass
public abstract class AbstractJpaPersistable<T extends Serializable> {

    private static final long serialVersionUID = -5554308939380869754L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private T id;

    public T getId() {
        return id;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != ProxyUtils.getUserClass(other)) return false;

        AbstractJpaPersistable<?> that = (AbstractJpaPersistable<?>) other;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return 31;
    }
}

