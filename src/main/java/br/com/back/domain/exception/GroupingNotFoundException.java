package br.com.back.domain.exception;

public class GroupingNotFoundException extends EntityNotFoundException{

	private static final long serialVersionUID = 1L;

    public GroupingNotFoundException(String message) {
        super(message);
    }
    
    public GroupingNotFoundException(Long groupingId) {
        this(String.format("Não existe um cadastro de grupo com código %d", groupingId));
    } 
	
}
