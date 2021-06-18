package com.cry.flutter.admin.wrapper;

import com.cry.flutter.admin.entity.Dict;
import com.cry.flutter.admin.entity.DictItem;
import lombok.Data;

import java.util.List;

@Data
public class DictWrapper {
    private Dict dict;
    private List<DictItem> dictItemList;
}
