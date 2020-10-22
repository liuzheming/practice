package t1_lambda.comparator;

import t1_lambda.Employe;

/**
 * Create by lzm on 2020/10/22
 */
@FunctionalInterface
public interface ICompareStrategy {

    boolean compare(Employe e1, Employe e2);

}