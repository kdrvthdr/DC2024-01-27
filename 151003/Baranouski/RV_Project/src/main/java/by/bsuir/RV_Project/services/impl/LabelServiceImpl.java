package by.bsuir.RV_Project.services.impl;

import by.bsuir.RV_Project.domain.Label;
import by.bsuir.RV_Project.dto.requests.LabelRequestDto;
import by.bsuir.RV_Project.dto.requests.converters.LabelRequestConverter;
import by.bsuir.RV_Project.dto.responses.LabelResponseDto;
import by.bsuir.RV_Project.dto.responses.converters.CollectionLabelResponseConverter;
import by.bsuir.RV_Project.dto.responses.converters.LabelResponseConverter;
import by.bsuir.RV_Project.exceptions.EntityExistsException;
import by.bsuir.RV_Project.exceptions.Messages;
import by.bsuir.RV_Project.exceptions.NoEntityExistsException;
import by.bsuir.RV_Project.repositories.LabelRepository;
import by.bsuir.RV_Project.services.LabelService;
import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Validated
public class LabelServiceImpl implements LabelService {

    private final LabelRepository labelRepository;
    private final LabelRequestConverter labelRequestConverter;
    private final LabelResponseConverter labelResponseConverter;
    private final CollectionLabelResponseConverter collectionLabelResponseConverter;

    @Override
    @Validated
    public LabelResponseDto create(@Valid @NonNull LabelRequestDto dto) throws EntityExistsException {
        Optional<Label> label = dto.getId() == null ? Optional.empty() : labelRepository.findById(dto.getId());
        if (label.isEmpty()) {
            return labelResponseConverter.toDto(labelRepository.save(labelRequestConverter.fromDto(dto)));
        } else {
            throw new EntityExistsException(Messages.EntityExistsException);
        }
    }

    @Override
    public Optional<LabelResponseDto> read(@NonNull Long id) {
        return labelRepository.findById(id).flatMap(label -> Optional.of(
                labelResponseConverter.toDto(label)));
    }

    @Override
    @Validated
    public LabelResponseDto update(@Valid @NonNull LabelRequestDto dto) throws NoEntityExistsException {
        Optional<Label> label = dto.getId() == null || labelRepository.findById(dto.getId()).isEmpty() ?
                Optional.empty() : Optional.of(labelRequestConverter.fromDto(dto));
        return labelResponseConverter.toDto(labelRepository.save(label.orElseThrow(() ->
                new NoEntityExistsException(Messages.NoEntityExistsException))));
    }

    @Override
    public Long delete(@NonNull Long id) throws NoEntityExistsException {
        Optional<Label> label = labelRepository.findById(id);
        labelRepository.deleteById(label.map(Label::getId).orElseThrow(() ->
                new NoEntityExistsException(Messages.NoEntityExistsException)));
        return label.get().getId();
    }

    @Override
    public List<LabelResponseDto> readAll() {
        return collectionLabelResponseConverter.toListDto(labelRepository.findAll());
    }
}
