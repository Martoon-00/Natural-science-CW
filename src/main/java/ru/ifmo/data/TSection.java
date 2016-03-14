package ru.ifmo.data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * System parameters at single slice of time
 */
public class TSection {
    /**
     * z slices
     */
    public final List<ZSection> zs;

    /**
     * front speed. This is am optional feature
     */
    public final double VF;

    public TSection(List<ZSection> zs) {
        this.zs = zs;

        // who will dare to count it?
        this.VF = 0;
    }

    public static List<TSection> extend(List<SimpleTSection> sections) {
		ArrayList<TSection> answer = new ArrayList<>();
		List<ZSection> list0 = sections.get(0)
				.zs.stream()
				.map(section -> new ZSection(section.T, section.X, 0))
				.collect(Collectors.toList());

		for (SimpleTSection sts : sections) {
			ArrayList<ZSection> list = new ArrayList<>();
			List<SimpleZSection> zs1 = sts.zs;
			for (int i = 0, zs1Size = zs1.size(); i < zs1Size; i++) {
				SimpleZSection szt = zs1.get(i);
				assert (szt.T - list0.get(i).T == 1);
				list.add(new ZSection(szt.T, szt.X, (szt.X - list0.get(i).X)));
			}
			list0 = new ArrayList<>(list);
			answer.add(new TSection(list));
		}

		return answer;
     }
}
