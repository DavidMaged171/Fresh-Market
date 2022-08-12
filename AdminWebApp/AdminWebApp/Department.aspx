<%@ Page Title="" Language="C#" MasterPageFile="~/master.Master" AutoEventWireup="true" CodeBehind="Department.aspx.cs" Inherits="AdminWebApp.Department" %>
<asp:Content ID="Content1" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
    <p style="font-weight: 700; font-size: x-large">
    <br />
    Department Page Control</p>
<p style="text-align: left">
    <asp:FormView ID="FormView1" runat="server" AllowPaging="True" CellPadding="4" DataKeyNames="sectionno" DataSourceID="SqlDataSource1" ForeColor="#333333" Width="1146px">
        <EditItemTemplate>
            sectionno:
            <asp:Label ID="sectionnoLabel1" runat="server" Text='<%# Eval("sectionno") %>' />
            <br />
            secttionname:
            <asp:TextBox ID="secttionnameTextBox" runat="server" Text='<%# Bind("secttionname") %>' />
            <br />
            sectionimage:
            <asp:TextBox ID="sectionimageTextBox" runat="server" Text='<%# Bind("sectionimage") %>' />
            <br />
            <asp:LinkButton ID="UpdateButton" runat="server" CausesValidation="True" CommandName="Update" Text="Update" />
            &nbsp;<asp:LinkButton ID="UpdateCancelButton" runat="server" CausesValidation="False" CommandName="Cancel" Text="Cancel" />
        </EditItemTemplate>
        <EditRowStyle BackColor="#2461BF" />
        <FooterStyle BackColor="#507CD1" Font-Bold="True" ForeColor="White" />
        <HeaderStyle BackColor="#507CD1" Font-Bold="True" ForeColor="White" />
        <InsertItemTemplate>
            secttionname:
            <asp:TextBox ID="secttionnameTextBox" runat="server" OnTextChanged="secttionnameTextBox_TextChanged" Text='<%# Bind("secttionname") %>' Width="30%" />
            <br />
            sectionimage:
            <asp:TextBox ID="sectionimageTextBox" runat="server" Height="50px" Text='<%# Bind("sectionimage") %>' TextMode="MultiLine" Width="90%" />
            <br />
            <asp:LinkButton ID="InsertButton" runat="server" CausesValidation="True" CommandName="Insert" Text="Insert" />
            &nbsp;<asp:LinkButton ID="InsertCancelButton" runat="server" CausesValidation="False" CommandName="Cancel" Text="Cancel" />
        </InsertItemTemplate>
        <ItemTemplate>
            sectionno:
            <asp:Label ID="sectionnoLabel" runat="server" Text='<%# Eval("sectionno") %>' />
            <br />
            secttionname:
            <asp:Label ID="secttionnameLabel" runat="server" Text='<%# Bind("secttionname") %>' />
            <br />
            sectionimage:
            <asp:Label ID="sectionimageLabel" runat="server" Text='<%# Bind("sectionimage") %>' />
            <br />
            <asp:LinkButton ID="EditButton" runat="server" CausesValidation="False" CommandName="Edit" Text="Edit" />
            &nbsp;<asp:LinkButton ID="DeleteButton" runat="server" CausesValidation="False" CommandName="Delete" Text="Delete" />
            &nbsp;<asp:LinkButton ID="NewButton" runat="server" CausesValidation="False" CommandName="New" Text="New" />
        </ItemTemplate>
        <PagerStyle BackColor="#2461BF" ForeColor="White" HorizontalAlign="Center" />
        <RowStyle BackColor="#EFF3FB" Width="100%" />
    </asp:FormView>
    <asp:SqlDataSource ID="SqlDataSource1" runat="server" ConnectionString="<%$ ConnectionStrings:db_a8b358_viadbConnectionString %>" DeleteCommand="DELETE FROM [section] WHERE [sectionno] = @sectionno" InsertCommand="INSERT INTO [section] ([secttionname], [sectionimage]) VALUES (@secttionname, @sectionimage)" SelectCommand="SELECT * FROM [section]" UpdateCommand="UPDATE [section] SET [secttionname] = @secttionname, [sectionimage] = @sectionimage WHERE [sectionno] = @sectionno">
        <DeleteParameters>
            <asp:Parameter Name="sectionno" Type="Int32" />
        </DeleteParameters>
        <InsertParameters>
            <asp:Parameter Name="secttionname" Type="String" />
            <asp:Parameter Name="sectionimage" Type="String" />
        </InsertParameters>
        <UpdateParameters>
            <asp:Parameter Name="secttionname" Type="String" />
            <asp:Parameter Name="sectionimage" Type="String" />
            <asp:Parameter Name="sectionno" Type="Int32" />
        </UpdateParameters>
    </asp:SqlDataSource>
</p>
<p style="text-align: left">
    &nbsp;</p>
<p style="text-align: left">
    <asp:GridView ID="GridView1" runat="server" AllowPaging="True" AllowSorting="True" AutoGenerateColumns="False" CellPadding="4" DataKeyNames="sectionno" DataSourceID="SqlDataSource1" ForeColor="#333333" GridLines="None" Width="1148px">
        <AlternatingRowStyle BackColor="White" />
        <Columns>
            <asp:CommandField ButtonType="Button" ShowDeleteButton="True" ShowEditButton="True" ShowSelectButton="True" />
            <asp:BoundField DataField="sectionno" HeaderText="sectionno" InsertVisible="False" ReadOnly="True" SortExpression="sectionno" />
            <asp:BoundField DataField="secttionname" HeaderText="secttionname" SortExpression="secttionname" />
            <asp:TemplateField HeaderText="Image">
                <ItemTemplate>
                    <asp:Image ID="Image1" runat="server" Height="69px" ImageUrl='<%# Eval("sectionimage") %>' Width="107px" />
                </ItemTemplate>
            </asp:TemplateField>
        </Columns>
        <EditRowStyle BackColor="#2461BF" />
        <FooterStyle BackColor="#507CD1" Font-Bold="True" ForeColor="White" />
        <HeaderStyle BackColor="#507CD1" Font-Bold="True" ForeColor="White" />
        <PagerStyle BackColor="#2461BF" ForeColor="White" HorizontalAlign="Center" />
        <RowStyle BackColor="#EFF3FB" />
        <SelectedRowStyle BackColor="#D1DDF1" Font-Bold="True" ForeColor="#333333" />
        <SortedAscendingCellStyle BackColor="#F5F7FB" />
        <SortedAscendingHeaderStyle BackColor="#6D95E1" />
        <SortedDescendingCellStyle BackColor="#E9EBEF" />
        <SortedDescendingHeaderStyle BackColor="#4870BE" />
    </asp:GridView>
</p>
</asp:Content>
