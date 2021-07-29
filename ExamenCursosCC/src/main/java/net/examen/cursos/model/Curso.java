package net.examen.cursos.model;

public class Curso {
	protected int id;
	protected String nombre;
	protected String profesor;
	protected String semestre;
	protected String pre_requisito;
	public Curso() {}
	public Curso(String nombre, String profesor, String semestre, String pre_requisito) {
		this.nombre = nombre;
		this.profesor = profesor;
		this.semestre = semestre;
		this.pre_requisito = pre_requisito;
	}
	public Curso(int id, String nombre, String profesor, String semestre, String pre_requisito) {
		this.id = id;
		this.nombre = nombre;
		this.profesor = profesor;
		this.semestre = semestre;
		this.pre_requisito = pre_requisito;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getProfesor() {
		return profesor;
	}
	public void setProfesor(String profesor) {
		this.profesor = profesor;
	}
	public String getSemestre() {
		return semestre;
	}
	public void setSemestre(String semestre) {
		this.semestre = semestre;
	}
	public String getPre_requisito() {
		return pre_requisito;
	}
	public void setPre_requisito(String pre_requisito) {
		this.pre_requisito = pre_requisito;
	}
}
