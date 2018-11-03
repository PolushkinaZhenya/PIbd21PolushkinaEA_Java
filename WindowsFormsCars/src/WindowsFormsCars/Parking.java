package WindowsFormsCars;

import java.util.ArrayList;
import java.awt.Color;
import java.awt.Graphics;

public class Parking<T extends ITransport> {
	ArrayList<T> _places;// / ������ ��������, ������� ������

	private int PictureWidth;// / ������ ���� ���������

	public int getPictureWidth() {
		return PictureWidth;
	}

	public void setPictureWidth(int value) {
		PictureWidth = value;
	}

	private int PictureHeight;// / ������ ���� ���������

	public int getPictureHeight() {
		return PictureHeight;
	}

	public void setPPictureHeighth(int value) {
		PictureHeight = value;
	}

	private int _placeSizeWidth = 210;// / ������ ������������ ����� (������)
	private int _placeSizeHeight = 80;// / ������ ������������ ����� (������)

	public Parking(int size, int pictureWidth, int pictureHeight) {// /
																	// �����������
		_places = new ArrayList<T>(size);
		this.PictureWidth = PictureWidth;
		this.PictureHeight = PictureHeight;
		for (int i = 0; i < size; i++) {
			_places.add(null);
		}
	}

	public int addTransport(T transport) {
		for (int i = 0; i < _places.size(); i++) {
			if (checkFreePlace(i)) {
				_places.add(i, transport);
				_places.get(i).SetPosition(5 + i / 5 * _placeSizeWidth + 5,
						i % 5 * _placeSizeHeight + 15, PictureWidth,
						PictureHeight);
				return i;
			}
		}
		return -1;
	}

	public T removeTransport(int index) {
		if (index < 0 || index > _places.size()) {
			return null;
		}
		if (!checkFreePlace(index)) {
			T car = _places.get(index);
			_places.set(index, null);
			return car;
		}
		return null;
	}

	private boolean checkFreePlace(int index) {// / ����� �������� ������������
												// ������������ ����� (������
												// �������)
		return _places.get(index) == null;
	}

	public void Draw(Graphics g) {// / ����� ��������� ��������
		DrawMarking(g);
		for (int i = 0; i < _places.size(); i++) {
			if (!checkFreePlace(i)) {
				_places.get(i).DrawTruckTrailer(g);
			}
		}
	}

	private void DrawMarking(Graphics g)// / ����� ��������� ��������
										// ����������� ����
	{
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, (_places.size() / 5) * _placeSizeWidth, 480);// �������
																		// ��������
		for (int i = 0; i < _places.size() / 5; i++) {// ������������, �� 5 ����
														// �� �����
			for (int j = 0; j < 6; ++j) {
				g.drawLine(i * _placeSizeWidth, j * _placeSizeHeight, i
						* _placeSizeWidth + 110, j * _placeSizeHeight);
			}
			g.drawLine(i * _placeSizeWidth, 0, i * _placeSizeWidth, 400);
		}
	}

}
