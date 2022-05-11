package com.example.facstest1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "facsimile")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Facsimile {
    @Id
    @Column(name = "facsimile_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "facsimile_name")
    private String name;

    @Column(name="facsimile_status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "facsimile_doctype")
    @Enumerated(EnumType.STRING)
    private DocumentType documentType;

    @Column(name = "facsimile_file_base64")
    private byte[] image;
}
