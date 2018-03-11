package com.jonzarate.buymefood;

/**
 * Created by JonZarate on 26/02/2018.
 */

public interface BaseView<T extends BasePresenter> {

    void setPresenter(T presenter);

}
